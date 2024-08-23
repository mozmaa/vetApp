import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  validPass: true,
  validRepPass: true,
  role: "USER",
  firstName: "",
  lastName: "",
  userName: "",
  eMail: "",
  newPassword: "",
  repeatedPassword: "",
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    replaceUser(state, action) {
      const {
        validPass = state.validPass,
        validRepPass = state.validRepPass,
        role = 'USER',
        firstName = state.firstName,
        lastName = state.lastName,
        userName = state.userName,
        eMail = state.eMail,
        newPassword = state.newPassword,
        repeatedPassword = state.repeatedPassword,
      } = action.payload;
      state.validPass = validPass;
      state.validRepPass = validRepPass;
      state.role = role;
      state.firstName = firstName;
      state.lastName = lastName;
      state.userName = userName;
      state.eMail = eMail;
      state.newPassword = newPassword;
      state.repeatedPassword = repeatedPassword;
    },
    updateField(state, action) {
        const {target , value} = action.payload
        state[target] = value
    }
  },
});

export const userAction = userSlice.actions;

export default userSlice.reducer;
