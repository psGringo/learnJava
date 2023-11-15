import {TNextStateOrError} from '@/Types/CustomStore';

export interface IAppState {
    name: string;
    nextState: TNextStateOrError;
    payload: string;
}

export interface IRootState {
    app: IAppState;
}
