import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import './App.css';

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

function createData(userName, realName, profileLink, posts, followers, following, description) {
    return {userName, realName, profileLink, posts, followers, following, description};
}

const rows = [
    createData('Frozen', 'Frozen yoghurt', '', 159, 6.0, 24, 'aaaa'),
    createData('Ice', 'Ice cream sandwich', '', 237, 9.0, 37, 'bbbb'),
    createData('Eclair', 'Eclair', '', 262, 16.0, 24, 'cccc'),
    createData('Cupcake', 'Cupcake', '', 305, 3.7, 67, 'ddddddd'),
    createData('Gingerbread', 'Gingerbread', '', 356, 16.0, 49, 'eeeee'),
];

export default function App() {
    const classes = useStyles();

    return (
        <Paper className={classes.root}>
            <Table className={classes.table}>
                <TableHead>
                    <TableRow>
                        <TableCell>User Name</TableCell>
                        <TableCell>Real Name</TableCell>
                        <TableCell align="center">Profile Link</TableCell>
                        <TableCell align="right">Posts</TableCell>
                        <TableCell align="right">Followers</TableCell>
                        <TableCell align="right">Following</TableCell>
                        <TableCell>Description</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map(row => (
                        <TableRow key={row.userName}>
                            <TableCell component="th" scope="row"> {row.userName}</TableCell>
                            <TableCell>{row.realName}</TableCell>
                            <TableCell align="center">{row.profileLink}</TableCell>
                            <TableCell align="right">{row.posts}</TableCell>
                            <TableCell align="right">{row.followers}</TableCell>
                            <TableCell align="right">{row.following}</TableCell>
                            <TableCell>{row.description}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Paper>
    );
}