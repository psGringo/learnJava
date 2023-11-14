import {useTranslation} from 'react-i18next';
import {i18Instance} from '@/i18n/config';
import {I18NEXT_NAMESPACE} from '@/i18n/Const';
import {TNextStateOrError} from '@/Types/CustomStore';
import {NextState} from '../openapi-generated';
import {ENTER_NAME_COMMAND, EXIT_COMMAND, NEXT_QUESTION_COMMAND, START_COMMAND} from '@/Const';
import {AlienApiService} from "@/ApiService/AlienApiService";
import {setAppState} from "@/Store/ActionCreators";

export const useTranslationTyped = () => {
    const {t, i18n} = useTranslation(I18NEXT_NAMESPACE, {i18n: i18Instance});
    const myT = t<string>;

    return {t: myT, i18n};
}

export const isTypeIsNextState = (obj: TNextStateOrError): obj is NextState => {
    return 'stateMachineResponse' in obj;
}


export const getButtonCaptions = () => {
    const buttonCaptionsMap = new Map<string, string>();

    buttonCaptionsMap.set(START_COMMAND, i18Instance.t('App.buttonsCaptions.start'));
    buttonCaptionsMap.set(ENTER_NAME_COMMAND, i18Instance.t('App.buttonsCaptions.enter_name'));
    buttonCaptionsMap.set(NEXT_QUESTION_COMMAND, i18Instance.t('App.buttonsCaptions.next_question'));
    buttonCaptionsMap.set(EXIT_COMMAND, i18Instance.t('App.buttonsCaptions.exit'));

    return buttonCaptionsMap;
}

export const getButtonHandler = (commandName: string, needPayload: boolean, dispatch, value: string) => {
    const handler = () => {
        const payload = needPayload ? value : undefined;

        AlienApiService.getNextState(commandName, payload).then((nextState) => {
            dispatch(setAppState(nextState));
        })
    }

    return handler;
}
