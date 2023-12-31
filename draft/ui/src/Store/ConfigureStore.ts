import {combineReducers, createStore} from 'redux';
import {appReducer} from '@/Store/AppReducer';
import {IRootState} from '@/Types/StoreModel';

const initStore = () => {

    return createStore(
        combineReducers<IRootState>({
            app: appReducer
        })
    );
}

export const appStore = initStore();
