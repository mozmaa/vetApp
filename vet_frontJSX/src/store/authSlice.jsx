import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: {
    isLoggedIn: false,
    role: '',
    name: '',
    message: ''
  },
  reducers: {
    replaceUser(state, action) {
        const {isLoggedIn, role, name} = action.payload
        state.isLoggedIn = isLoggedIn
        state.role = role
        state.name = name
        state.message = ''
    },
    loginFail(state , action) {
        state.isLoggedIn = false
        state.role = ''
        state.name = ''
        state.message = action.payload
    }
  }
});

export const authActions = authSlice.actions

export default authSlice.reducer




