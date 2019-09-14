import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import React from "react";
import TableHead from "@material-ui/core/TableHead";
import Table from "@material-ui/core/Table";

export default class UserComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/user", {mode: 'no-cors'})
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result.items
                    });
                },

                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const {error, isLoaded, items} = this.state;
        if (error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <Table>
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
                        {items.map(item => (
                            <TableRow key={item.userName}>
                                <TableCell component="th" scope="row"> {item.userName}</TableCell>
                                <TableCell>{item.realName}</TableCell>
                                <TableCell align="center">{item.profileLink}</TableCell>
                                <TableCell align="right">{item.posts}</TableCell>
                                <TableCell align="right">{item.followers}</TableCell>
                                <TableCell align="right">{item.following}</TableCell>
                                <TableCell>{item.description}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            );
        }
    }

}