import {SET_APP_STATE, SET_PAYLOAD} from '@/Store/ActionTypes';
import {ICustomAction, TNextStateOrError} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';

export const setAppState: (nextState: TNextStateOrError) => ICustomAction<IAppState> = (
    nextState
) => ({
    type: SET_APP_STATE,
    payload: {nextState}
})

export const setPayload: (payload: string) => ICustomAction<IAppState> = (
    payload
) => ({
    type: SET_PAYLOAD,
    payload: {payload}
})
