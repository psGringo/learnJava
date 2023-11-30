import {Greeting} from '../../openapi-generated/greeting';
import {GameState} from '../../openapi-generated/lifegame';
import {SET_APP_STATE, SET_GAME_STATE} from '@/Store/ActionTypes';
import {ICustomAction} from '@/Types/CustomStore';
import {IAppState} from '@/Types/StoreModel';


export const setGreeting: (greeting: Greeting) => ICustomAction<IAppState> = (greeting) => ({
    type: SET_APP_STATE,
    payload: {greeting}
})

export const setGameState: (gameState: GameState) => ICustomAction<IAppState> = (gameState) => ({
    type: SET_GAME_STATE,
    payload: {gameState}
})

//
