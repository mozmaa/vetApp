import { jwtDecode } from 'jwt-decode'
import Axios from '../apis/Axios.jsx'


export const register = async (body, navigate) => {
    
    try {
        await Axios.post('/users' , body)
        navigate ('/signIn', {replace: true})
    } catch (error){
        console.log(error)
        alert("Regist failed")
    }
}

export const login = async (body , navigate) => {

    try{
        const response = await Axios.post("/users/auth", body)
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