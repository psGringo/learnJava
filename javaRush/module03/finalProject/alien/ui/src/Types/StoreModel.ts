import {TNextStateOrError} from '@/Types/CustomStore';

export interface IAppState {
    name: string;
    nextState: TNextStateOrError;
}

export interface IRootState {
    app: IAppState;
}
