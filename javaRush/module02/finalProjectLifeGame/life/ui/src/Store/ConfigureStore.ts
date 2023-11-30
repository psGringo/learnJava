import {combineReducers, createStore} from 'redux';
import {appReducer} from '@/Store/AppReducer';
import {IRootState} from '@/Types/StoreModel';


export const store
createStore(
    combineReducers<IRootState>({
        app: appReducer
    })
);




