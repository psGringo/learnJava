import {combineReducers, createStore} from "redux";
import {IRootState} from "@/Types/StoreModel";
import {appReducer} from "@/Store/AppReducer";

export const initStore = () => {

    return createStore(
        combineReducers<IRootState>({
            app: appReducer
        })
    );
}

export const appStore = initStore();