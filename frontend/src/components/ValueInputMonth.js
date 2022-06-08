import React, { useState } from 'react'
import TextField from '@mui/material/TextField'


const ValueInputMonth = (props) => {
    const [month, setMonth] = useState('')
    const [monthError, setMonthError] = useState('')

    const handleBlur = (e) => {
        const month = e.target.value
        if (!month) {
            setMonthError('required')
        } else if (month < 0 || 12 < month) {
            setMonthError('from 1 to 12')
        } else {
            setMonthError()
        }
    }

    return (
        <>
            <TextField
                id="input-month"
                label="Birth Month"
                valieant="outlined"
                inputProps={month}
                onChange={(e) => {
                    props.setValue({ ...props.value, [props.title]: e.target.value })
                    setMonth(e.target.value)
                }}
                onBlur={handleBlur}
                margin="normal"
            />
        </>
    )
};

export default ValueInputMonth;