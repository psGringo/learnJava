import {SET_APP_STATE} from '@/Store/ActionTypes';
import {ICustomAction, TNextStateOrError} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';

export const setAppState: (nextState: TNextStateOrError) => ICustomAction<IAppState> = (
    nextState
) => ({
    type: SET_APP_STATE,
    payload: {nextState}
})
