package networks.service.impl;

import java.net.Socket;

import networks.dao.SocketDao;
import networks.dao.impl.SocketDaoImpl;
import networks.service.SocketService;

public class SocketServiceImpl implements SocketService {

	SocketDao dao = new SocketDaoImpl();

	@Override
	public byte[] read(Socket socket) {
		return dao.read(socket);
	}

	@Override
	public void close(Socket socket) {
		dao.close(socket);
	}

	@Override
	public boolean send(Socket socket, byte[] sendData) {
		return dao.send(socket, sendData);
	}

	@Override
	public Socket open(String host, int port) {
		return dao.open(host, port);
	}
}
