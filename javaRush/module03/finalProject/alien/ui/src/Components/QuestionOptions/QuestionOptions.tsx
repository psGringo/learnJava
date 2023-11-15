import {FormControl, FormControlLabel, FormLabel, Radio, RadioGroup} from '@mui/material';
import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {isTypeIsNextState} from '@/Utils';
import {setPayload} from '@/Store/ActionCreators';

export const QuestionOptions: React.FC = () => {

    const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);

    const [value, setValue] = React.useState('female');
    
    const dispatch = useDispatch();

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = (event.target as HTMLInputElement).value;

        setValue(value);
        dispatch(setPayload(value))
    };

    const getOptions = () => {
        if (isTypeIsNextState(nextState)) {

            const options: React.ReactNode[] = [];

            nextState.stateMachineResponse.question?.options?.forEach((option) => {
                options.push(<FormControlLabel control={<Radio/>} label={option.value} value={option.value}/>)
            })

            return <div>{options}</div>
        }

        return undefined;
    }

    return (
        <div>
            {/* eslint-disable-next-line react/react-in-jsx-scope */}
            <FormControl>
                <FormLabel id="demo-controlled-radio-buttons-group" />
                <RadioGroup
                    name="controlled-radio-buttons-group"
                    onChange={handleChange}
                    value={value}
                >
                    {getOptions()}
                </RadioGroup>
            </FormControl>
        </div>
    );
}