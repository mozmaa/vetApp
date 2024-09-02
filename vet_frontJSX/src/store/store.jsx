import { configureStore } from "@reduxjs/toolkit";

import authReducer from './auth/auth_slice'
import userReducer from './user/user_slice'
import ambulanceReducer from './ambulance/ambulance_slice'
import { loadFromLocalStorage, saveToLocalStorage } from "../utils/local_storage_utils";

const preloadedState = loadFromLocalStorage();

const store = configureStore({
    reducer: {auth: authReducer, user: userReducer , ambulances: ambulanceReducer},
    preloadedState,
})

store.subscribe(() => {
    const state = store.getState()
    saveToLocalStorage({auth: state.auth});
  });
  
export default store