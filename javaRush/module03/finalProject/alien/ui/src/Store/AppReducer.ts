/* eslint default-param-last: 0 */                // --> OFF
import {i18Instance} from '@/i18n/config';
import {SET_APP_STATE} from '@/Store/ActionTypes';
import {ICustomReducer, TNextStateOrError} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';
import {START_COMMAND} from '@/Const';

const initialNextState: TNextStateOrError = {
    stateMachineResponse: {
        message: '',
        renderComponentName: START_COMMAND,
        question: {
            text: '',
            options: []
        },
        nextCommands: []
    }
}

const initialState: IAppState = {
    name: i18Instance.t<string>('App.name'),
    nextState: initialNextState
}

export const appReducer: ICustomReducer<IAppState> = (
    state = initialState,
    action,
) => {
    const nextState = {
        ...state,
        ...action.payload
    }

    console.log('here ', nextState);

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
