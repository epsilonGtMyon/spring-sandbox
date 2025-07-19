const endpointSelectElem = document.getElementById("endpointSelect");
const fetchButtonElem = document.getElementById("fetchButton");
const locationHrefButtonElem = document.getElementById("locationHrefButton");

function resolveEndpoint() {
  const selected = endpointSelectElem.value;

  return `${contextPath}download01/${selected}`;
}

function resolveFileName(headers) {
  const disposition = headers.get("Content-Disposition");
  let filename = undefined;

  if (disposition) {
    // filename* を優先して取得
    const matchFilenameStar = disposition.match(/filename\*=([^;]+)/i);
    if (matchFilenameStar) {
      // 例: filename*=UTF-8''%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB.txt
      const value = matchFilenameStar[1].trim();
      // エンコーディング指定を省いてデコード
      const filenameEncoded = value.replace(/^UTF-8''/i, "");
      filename = decodeURIComponent(filenameEncoded);
    } else {
      // フォールバック: 通常のfilename
      const matchFilename = disposition.match(/filename="?(.*?)"?($|;)/i);
      if (matchFilename) {
        filename = matchFilename[1];
      }
    }
  }
  return filename;
}

async function fetchFile(url) {
  console.log("[request]:begin");
  const resp = await fetch(url);
  console.log("[request]:end", resp);

  const fileSize = Number(resp.headers.get("content-length"));
  const fileName = resolveFileName(resp.headers);

  return {
    rawResponse: resp,
    fileSize,
    fileName,
  };
}

fetchButtonElem.addEventListener("click", async () => {
  const endpoint = resolveEndpoint();
  const { rawResponse, fileSize, fileName } = await fetchFile(endpoint);

  const blob = await rawResponse.blob();

  try {
    const a = document.createElement("a");
    a.href = URL.createObjectURL(blob);
    a.target = "_blank";
    a.download = fileName;
    a.click();
  } finally {
    URL.revokeObjectURL(blob);
  }
});

locationHrefButtonElem.addEventListener("click", async () => {
  const endpoint = resolveEndpoint();
  location.href = endpoint;
});
