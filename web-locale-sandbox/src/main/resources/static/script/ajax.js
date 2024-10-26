const { AjaxClient, useAjax } = (function () {
  class AjaxClient {
    async get(url, params, optionCustomizer) {
      const query = new URLSearchParams(params ?? {});

      const headers = new Headers();
      headers.append("X-Requested-With", "XMLHttpRequest");
      const option = {
        method: "GET",
        headers,
      };

      if (optionCustomizer != null) {
        optionCustomizer(option);
      }

      const resp = await fetch(`${url}?${query.toString()}`, option);

      try {
        return await resp.json();
      } catch (err) {
        return null;
      }
    }

    async post(url, params, optionCustomizer) {
      const headers = new Headers();
      headers.append("Content-Type", "application/json");
      headers.append("X-Requested-With", "XMLHttpRequest");

      const option = {
        method: "POST",
        body: JSON.stringify(params),
        headers,
      };

      if (optionCustomizer != null) {
        optionCustomizer(option);
      }
      const resp = await fetch(`${url}`, option);

      try {
        return await resp.json();
      } catch (err) {
        return null;
      }
    }
  }

  const ajax = new AjaxClient();

  function useAjax() {
    return ajax;
  }

  return { AjaxClient, useAjax };
})();
