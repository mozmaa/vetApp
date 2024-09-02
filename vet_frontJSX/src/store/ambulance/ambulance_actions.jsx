import { ambulanceActions } from "./ambulance_slice";
import { fetchAmbulances, register, deleteAmbulance, edit } from "../../services/ambulance_service";

export const fetchAmbulanceData = (searchParams) => {
  return async (dispatch) => {
    try{
        
        const response = await fetchAmbulances(searchParams)
        dispatch(ambulanceActions.replaceInitialState({
            ambulances: response.data,
            pageNo: searchParams.pageNo,
            pageCount: Number(response.headers['total-pages'])
        }))
    } catch (error) {
        console.log(error.mesagge)
    }
   };


};

export const editAmbulance = (body, navigate) => {
    return async () => {
        try {
            await edit(body, navigate);
        } catch (error) {
            console.log(error)
        }
    };
}

export const registerAmulance = (id, body, navigate) => {
    return async () => {
        try {
            await register(id, body, navigate);
        } catch (error) {
            console.log(error)
        }
    };
}

export const deleteAmbulanceById = (id, navigate) => {
    return async () => {
        try{
            await deleteAmbulance(id, navigate)
        }catch (error){
            console.log(error)
        }
    }
}

