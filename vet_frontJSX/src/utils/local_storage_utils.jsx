export const saveToLocalStorage = (state) => {
  try {
    const serializedState = JSON.stringify({ auth: state.auth });
    localStorage.setItem("authState", serializedState);
  } catch (err) {
    console.error("Could not save state", err);
  }
};

export const loadFromLocalStorage = () => {
  try {
    const serializedState = localStorage.getItem("authState");
    if (serializedState === null) return undefined;
    return JSON.parse(serializedState);
  } catch (err) {
    console.error("Could not load state", err);
    return undefined;
  }
};

export const deleteFromLocalStorage = () => {
  localStorage.removeItem("authState");
};
