import { useWindowId } from "../common/windowId.js";

const windowIdElem = document.getElementById("windowId");
const txTokenElem = document.getElementById("txToken");
const statusElem = document.getElementById("status");

const openWindowButtonElem = document.getElementById(
  "openWindowButton"
);
const publishTxTokenButtonElem = document.getElementById(
  "publishTxTokenButton"
);
const noActionButtonElem = document.getElementById("noActionButton");
const checkTxTokenButtonElem = document.getElementById("checkTxTokenButton");
const throwException1ButtonElem = document.getElementById("throwException1Button");
const throwException2ButtonElem = document.getElementById("throwException2Button");
const throwException3ButtonElem = document.getElementById("throwException3Button");

// ----------------------------------------------------------

let txTokenHolder = "";

const windowId = useWindowId();
windowIdElem.value = windowId

function assingToken(txTokenValue) {
  if (txTokenValue == null) {
    return;
  }
  txTokenHolder = txTokenValue;
  txTokenElem.value = txTokenValue;
}

function applyResult(resp) {
  statusElem.textContent = resp.status
}

openWindowButtonElem.addEventListener("click", () => {
  window.open("/sandbox01?open=true")
})

publishTxTokenButtonElem.addEventListener("click", async () => {
  const resp = await fetch("/sandbox01/publishTxToken", {
    method: "POST",
    headers: { 
      "Content-Type": "application/json",
      "X-Window-Id": windowId
     },
    body: JSON.stringify({}),
  });

  applyResult(resp)
  const txToken = resp.headers.get("X-Tx-Token");
  assingToken(txToken);
});

noActionButtonElem.addEventListener("click", async () => {
  const resp = await fetch("/sandbox01/noAction", {
    method: "POST",
    headers: { 
      "Content-Type": "application/json",
      "X-Window-Id": windowId
     },
    body: JSON.stringify({}),
  });

  applyResult(resp)
  const txToken = resp.headers.get("X-Tx-Token");
  assingToken(txToken);
});

checkTxTokenButtonElem.addEventListener("click", async () => {
  const resp = await fetch("/sandbox01/checkTxToken", {
    method: "POST",
    headers: { 
      "Content-Type": "application/json" ,
      "X-Window-Id": windowId,
      "X-Tx-Token": txTokenHolder
    },
    body: JSON.stringify({}),
  });

  applyResult(resp)
  const txToken = resp.headers.get("X-Tx-Token");
  assingToken(txToken);
});


async function handleThrowExceptionButton(url) {
  const resp = await fetch(url, {
    method: "POST",
    headers: { 
      "Content-Type": "application/json" ,
      "X-Window-Id": windowId,
      "X-Tx-Token": txTokenHolder
    },
    body: JSON.stringify({}),
  });

  applyResult(resp)
  const txToken = resp.headers.get("X-Tx-Token");
  assingToken(txToken);
}

throwException1ButtonElem.addEventListener("click", async () => {
  await handleThrowExceptionButton("/sandbox01/throwException1")
});
throwException2ButtonElem.addEventListener("click", async () => {
  await handleThrowExceptionButton("/sandbox01/throwException2")
});
throwException3ButtonElem.addEventListener("click", async () => {
  await handleThrowExceptionButton("/sandbox01/throwException3")
});