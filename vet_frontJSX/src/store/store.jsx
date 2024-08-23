import { configureStore } from "@reduxjs/toolkit";

import authReducer from './authSlice'
import userReducer from './user_slice'
import { loadFromLocalStorage, saveToLocalStorage } from "../utils/local_storage_utils";

const preloadedState = loadFromLocalStorage();


const store = configureStore({
    reducer: {auth: authReducer, user: userReducer},
    preloadedState,
})

store.subscribe(() => {
    const state = store.getState()
    saveToLocalStorage({auth: state.auth});
  });
  
export default store