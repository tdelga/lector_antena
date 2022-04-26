SERVICIO LECTOR RFID

Requerimientos

1- sudo apt install default-jre

2- sudo apt install default-jdk

3- Conexion LAN en PRIMER puerto

4- IPv4 manual LAN 192.168.1.201


Configuracion y Ejecucion

1- Descarga el proyecto LECTOR_RFID en /home/user/

2- Otorga permisos completos al directorio
 
2- Crear archivo de servicio /etc/systemd/system/reader.service con siguiente contenido

  [Unit]
  Description= reader script

  [Service]
  ExecStart=/home/user/LECTOR_RFID/start_lector.sh

  [Install]
  WantedBy=multi-user.target
  
4- systemctl enable reader , esto habilita el servicio para el booteo

PARA TESTEO systemctl start reader

PARA DEBUG systemctl status reader
  

