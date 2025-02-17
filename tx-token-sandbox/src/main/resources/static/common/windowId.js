let windowId = "";

function useWindowId() {
  if (windowId === "") {
    const param = new URLSearchParams(location.search);
    const opened = param.get("open") === "true";

    if (opened) {
      const url = location.href.split("?")[0];
      location.replace(url);
    }

    const sessionWindowId = sessionStorage.getItem("windowId");
    if (!opened && sessionWindowId != null) {
      windowId = sessionWindowId;
    } else {
      windowId = crypto.randomUUID();
      sessionStorage.setItem("windowId", windowId);
    }
  }

  return windowId;
}

export { useWindowId };
