import {Error, NextState} from '../../openapi-generated';

export interface ICustomAction<stateType> {
    type: string;
    payload: Partial<stateType>;
}

export interface ICustomReducer<stateType> {
    (state: stateType | undefined, action: ICustomAction<stateType>): stateType;
}

export type TNextStateOrError = NextState | Error;
