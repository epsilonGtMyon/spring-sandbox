const fileElem = document.getElementById("file");
const formFileButtonElem = document.getElementById("formFileButton");
const base64part1ButtonElem = document.getElementById("base64part1Button");
const base64part2ButtonElem = document.getElementById("base64part2Button");

function encodeFileToBase64(file) {
  return new Promise((resolve) => {
    if(file == null) {
        resolve(null)
        return
    }


    const reader = new FileReader();
    reader.onload = (ev) => {
      const dataUrl = ev.target.result;
      const base64Encoded = dataUrl.split(",")[1];
      resolve(base64Encoded);
    };
    reader.readAsDataURL(file);
  });
}

formFileButtonElem.addEventListener("click", async () => {
  const file = fileElem.files[0];
  if (file == null) {
    return;
  }

  const formData = new FormData();
  formData.append("file", file);

  await fetch(`${contextPath}upload01/formFile`, {
    method: "post",
    body: formData,
  });
});

base64part1ButtonElem.addEventListener("click", async () => {
  const file = fileElem.files[0];
  if (file == null) {
    return;
  }

  const base64File = await encodeFileToBase64(file);
  await fetch(`${contextPath}upload01/base64RawText`, {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ base64Text: base64File }),
  });
});


base64part2ButtonElem.addEventListener("click", async () => {
  const file = fileElem.files[0];

  const base64File = await encodeFileToBase64(file);
  await fetch(`${contextPath}upload01/base64Data`, {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ base64Data: base64File }),
  });
});