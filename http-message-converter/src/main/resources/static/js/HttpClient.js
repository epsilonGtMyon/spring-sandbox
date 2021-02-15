//単純なajax用のクラス
class HttpClient {
  static async get(url, param) {
    const endpointUrl = `${url}?${QueryString.stringify(param)}`;

    const headers = {
    };

    const response = await fetch(endpointUrl, {
      method: "GET",
      credentials: "include",
      headers,
    });
    return await response.json();
  }

  static async postAsJson(url, data) {
    const endpointUrl = url;

    const headers = {
      "Content-type": "application/json",
    };

    const response = await fetch(endpointUrl, {
      method: "POST",
      credentials: "include",
      headers,
      body: JSON.stringify(data),
    });

    return await response.json();
  }
}
