/* eslint default-param-last: 0 */    // --> OFF
import {i18Instance} from '@/i18n/config';
import {SET_APP_STATE, SET_GAME_STATE} from '@/Store/ActionTypes';
import {ICustomReducer} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';

const initialState: IAppState = {
    name: i18Instance.t<string>('App.name'),
    greeting: null,
    gameState: {gameMap: {maxX: 10, maxY: 10}, grass: [], animals: []}
}

export const appReducer: ICustomReducer<IAppState> = (
    state = initialState,
    action,
) => {
    switch (action.type) {
        case SET_APP_STATE:
        case SET_GAME_STATE:
            return {
                ...state,
                ...action.payload
            }

        default:
            return state;
    }
}
