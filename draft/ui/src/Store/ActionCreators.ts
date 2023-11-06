import {ICustomAction} from "@/Types/CustomStore";
import {IAppState} from "@/Types/StoreModel";
import {SET_APP_STATE} from '@/Store/ActionTypes';


export const setAppState: (appState: IAppState) => ICustomAction<IAppState> = (appState) => ({
    type: SET_APP_STATE,
    payload: appState
})