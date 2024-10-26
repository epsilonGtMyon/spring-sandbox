(function () {
  const ajax = useAjax();
  const resultElem = document.getElementById("result");
  const langElem = document.getElementById("lang");
  const hello1ButtonElem = document.getElementById("hello1Button");
  const hello2ButtonElem = document.getElementById("hello2Button");
  const hello3ButtonElem = document.getElementById("hello3Button");

  function setResultText(text) {
    resultElem.value = text;
  }

  async function processHello(helloMethod) {
    setResultText("");
    const lang = langElem.value;

    const resp = await ajax.get(`/sandbox01${helloMethod}`, null, (p) => {
      if (lang !== "") {
        p.headers.append("accept-language", lang);
      }
    });
    console.log(resp);
    setResultText(resp.message);
  }

  hello1ButtonElem.addEventListener("click", async () => {
    await processHello("/hello1");
  });

  hello2ButtonElem.addEventListener("click", async () => {
    await processHello("/hello2");
  });

  hello3ButtonElem.addEventListener("click", async () => {
    await processHello("/hello3");
  });

  //--------------
  const setLangElem = document.getElementById("setLang");
  const setLocaleButtonElem = document.getElementById("setLocaleButton");

  setLocaleButtonElem.addEventListener("click", async () => {
    setResultText("");
    const lang = setLangElem.value;
    const param = {
      lang,
    };

    const resp = await ajax.post("/sandbox01/setLocale", param);
    console.log(resp);
  });
})();
