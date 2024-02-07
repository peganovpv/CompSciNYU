// net modules
// create tcp/ip server
// no application layer protocol

import { createServer } from 'net';

const handleData = (socket, data) => {
    console.log(data.toString())
    socket.write('HTTP/1.1 200 OK\r\n')
    socket.write('Content-Type: text/html\r\n')
    socket.write('\r\n')
    socket.write('<html><body><h1>Hello World</h1></body></html>')
    socket.end()
}

const server = createServer((socket) => {

    socket.on('data', (data) => {
        handleData(socket, data)
    });

    socket.on('end', () => {
        console.log('client disconnected');
    });

    socket.write('HTTP/1.1 200 OK\r\n')
    socket.write('Content-Type: text/html\r\n')
    socket.write('\r\n')
    socket.write('<html><body><h1>Hello World</h1></body></html>')
    socket.end()

});

const PORT = 3000
const HOST = '127.0.0.1'

server.listen(PORT, HOST, () => {
    console.log('server bound')
})
