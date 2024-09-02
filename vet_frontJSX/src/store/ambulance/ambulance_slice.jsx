import { createSlice } from "@reduxjs/toolkit";

const ambulanceSLice = createSlice({
    name: 'ambulance',
    initialState: {
        ambulancesArray: [],
        pageNo: 1,
        pageCount: 0
    },
    reducers:{
        replaceInitialState(state, action) {
            const {pageNo, ambulances, pageCount} = action.payload
            state.ambulancesArray = ambulances
            state.pageNo = pageNo
            state.pageCount = pageCount
        }
    }
});

export const ambulanceActions = ambulanceSLice.actions 

export default ambulanceSLice.reducer
