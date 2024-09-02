import Axios from '../apis/Axios.jsx'

export const fetchAmbulances = async(searchParams) => {
    try{
        const response = await Axios.get('/ambulances' , {params: searchParams})
        return response
    } catch (error) {
        console.log(error)
        alert('Error while fetching ambulances')
    }
}

export const register = async(body, navigate) => {

    try{
        await Axios.post('/ambulances', body)
        alert("Registrated")
        navigate ('/ambulances')
    } catch(error){
        console.log(error)
        alert("Registering failed")
    }

}

export const edit = async(id, body, navigate) => {

    try{
        await Axios.put(`/ambulances/${id}`, body)
        alert("Edited")
        navigate('/ambulances')
    } catch(error){
        console.log(error)
        alert("Editing failed")
    }

}

export const deleteAmbulance = async(id, navigate) => {

    try{
        await Axios.delete(`/ambulances/${id}` , id)
        alert("Deleted")
        navigate (0)
    } catch(error){
        console.log(error)
        alert("Deleting failed")
    }
}