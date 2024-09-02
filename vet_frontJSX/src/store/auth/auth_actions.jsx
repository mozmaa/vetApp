import { authActions } from "./auth_slice";
import { login, logout, register } from "../../services/user_service";

export const authenticateUser = (body, navigate) => {
    return async (dispatch) => {
        try {
            const response = await login(body, navigate);
            dispatch(authActions.replaceUser({
                isLoggedIn: true,
                role: response.role.authority,  
                name: response.sub
            }));
        } catch (error) {
            dispatch(authActions.loginFail(error.message)) 
        }
    };
};

export const registerUser = (body, navigate) => {
    return async (dispatch) => {
        try {
            await register(body, navigate);
        } catch (error) {
            dispatch(authActions.loginFail(error.message)); 
        }
    };
}

export const logoutUser = (navigate) => {
    return (dispatch) => {
        logout(navigate)
        dispatch (authActions.replaceUser({
            isLoggedIn: false,
            role: '',  
            name: '' 
        }))
    }
}
