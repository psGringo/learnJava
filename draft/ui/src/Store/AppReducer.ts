/* eslint default-param-last: 0 */  // --> OFF
import {SET_APP_STATE} from '@/Store/ActionTypes';
import {ICustomReducer} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';

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