import {Greeting} from '../../openapi-generated';
import {SET_APP_STATE} from '@/Store/ActionTypes';
import {ICustomAction} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';


export const setAppState: (greeting: Greeting) => ICustomAction<IAppState> = (greeting) => ({
    type: SET_APP_STATE,
    payload: {greeting}
})
