
import TestAxios from "../apis/Axios.jsx"

export const login = async (body) => {
    console.log('body' , body)

    try{
        const resp = await TestAxios.post("/users/auth", body)
        window.localStorage.setItem("jwt", resp.data)
        window.location.replace("http://localhost:5173")
    }catch(e){
        console.log(e)
        alert("Login FAILED")
    }
}

export const logout = () => {
    window.localStorage.removeItem("jwt")
    window.location.replace("http://localhost:5173")
}