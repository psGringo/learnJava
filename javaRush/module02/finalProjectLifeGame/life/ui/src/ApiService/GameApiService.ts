import {CancelablePromise, GameState, LifeGameService, Error} from '../../openapi-generated/lifegame';

interface IGameApiService {
    getGameState(): CancelablePromise<GameState | Error>;
}

export const GameApiService: IGameApiService = class GreetingsApiService {

    public static getGameState(): CancelablePromise<GameState | Error> {
        return new CancelablePromise<GameState | Error>((resolve, reject) => {
            LifeGameService.getGameState().then((response) => {
                resolve(response as GameState);
            }).catch((e) => {
                reject(e);
            })
        });
    }

}
