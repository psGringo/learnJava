import {Greeting} from '../../openapi-generated/greeting';
import {GameState} from '../../openapi-generated/lifegame';

export interface IAppState {
    name: string;
    greeting: Greeting | null;
    gameState: GameState;
}

export interface IRootState {
    app: IAppState;
}
