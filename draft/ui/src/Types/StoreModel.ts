import {Greeting} from "../../openapi-generated";

export interface IAppState {
    name: string;
    greeting: Greeting | null;
}

export interface IRootState {
    app: IAppState;
}