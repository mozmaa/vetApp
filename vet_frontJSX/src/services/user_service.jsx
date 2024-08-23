import { jwtDecode } from 'jwt-decode'
import TestAxios from '../apis/Axios.jsx'


export const register = async (body, navigate) => {
    
    try {
        await TestAxios.post('/users' , body)
        navigate ('/signIn', {replace: true})
    } catch (error){
        console.log(error)
        alert("Regist failed")
    }
}

export const login = async (body , navigate) => {

    try{
        const response = await TestAxios.post("/users/auth", body)
        console.log('login data', response.data)
        window.localStorage.setItem("jwt", response.data)
        navigate ('/', {replace: true})
        const jwt = localStorage.getItem ? jwtDecode(localStorage.getItem('jwt')) : null
        return jwt
    }catch(e){
        console.log(e)
        alert("Login FAILED")
    }
}

export const logout = (navigate) => {
    window.localStorage.removeItem("jwt")
    window.localStorage.removeItem("authState")
    navigate ('/', {replace: true})
}