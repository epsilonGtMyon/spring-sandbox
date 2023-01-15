const AjaxClient = (function () {
  const _token = document
    .querySelector("meta[name=_csrf]")
    .getAttribute("content");
  const _tokenHeader = document
    .querySelector("meta[name=_csrf_header]")
    .getAttribute("content");

  class AjaxClient {
    // とりあえずのハンドリング
    static async handleResponse(response) {
      if (response.status === 200) {
        const responseBody = await response.text();
        return JSON.parse(responseBody);
      }

      if (response.status === 401) {
        window.alert("セッションきれた");
        location.href = "/login?timeout";
      } else if (response.status === 403) {
        window.alert("権限がない");
      }

      throw new Error();
    }

    static async get(url) {
      const response = await fetch(url, {
        method: "GET",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "X-Requested-With": "XMLHttpRequest",
        },
        referrerPolicy: "no-referrer",
      });

      return AjaxClient.handleResponse(response);
    }

    static async post(url, param) {
      const response = await fetch(url, {
        method: "POST",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "X-Requested-With": "XMLHttpRequest",
          "Content-Type": "application/json",
          [_tokenHeader]: _token,
        },
        referrerPolicy: "no-referrer",
        body: JSON.stringify(param || {}),
      });

      return AjaxClient.handleResponse(response);
    }
  }

  return AjaxClient;
})();