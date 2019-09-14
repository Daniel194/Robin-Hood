import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import './App.css';
import UserComponent from './UserComponent'

const useStyles = makeStyles(theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing(3),
        overflowX: 'auto',
    },
    table: {
        minWidth: 650,
    },
}));

export default function App() {
    const classes = useStyles();

    return (
        <Paper className={classes.root}>
            <UserComponent className={classes.table}></UserComponent>
        </Paper>
    );
}