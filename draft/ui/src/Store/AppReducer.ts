import {IAppState} from "@/Types/StoreModel";
import {ICustomReducer} from "@/Types/CustomStore";
import {SET_APP_STATE} from "@/Store/ActionTypes";

const initialState: IAppState = {
    name: 'draft',
    greeting: null
}

export const appReducer: ICustomReducer<IAppState> = (
    state = initialState,
    action,
) => {
    switch (action.type) {
        case SET_APP_STATE:
            return {
                ...state,
                ...action.payload
            }
        default:
            return state;
    }
}