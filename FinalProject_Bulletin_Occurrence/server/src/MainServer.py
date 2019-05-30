import socket
import json
# ------------------------> Functions <--------------------------------


def StrToJson(msg):
    return json.loads(msg)

def setAll(msg):
    msgjson = StrToJson(msg)
    person = Person()
    bo = OccurrenceBoletin()
    msgRequest = Message()
    person.setPerson(JSON_MSG=msgjson)
    bo.setOccurrenceBoletin(JSON_MSG=msgjson)
    msgRequest.setMessage(JSON_MSG=msgjson)
    return (msgRequest,bo,person)
# ------------------------> Class <------------------------------------


class SocketServer:
    def __init__(self,serverPort=2000,serverHost="127.0.0.1"):
        self.PortServer = serverPort
        self.HostServer = serverHost
        self.socket = socket.socket()
        self.socket.bind((self.HostServer,self.PortServer))
        self.socket.listen(5)
        pass

    def read(self,conn):
        msg=str()
        length_of_message = int.from_bytes(conn.recv(2), byteorder='big')
        msg = conn.recv(length_of_message).decode("UTF-8")
        return msg

    def send(self,msg,conn):
        conn.send(len(str(msg).encode("UTF-8")).to_bytes(2, byteorder='big'))
        conn.send(str(msg).encode("UTF-8"))


class Person:
    def __init__(self):
        self.Name = None
        self.CPF = None
        self.RG = None
        self.Date_Of_Birth = None
        self.Sex = None
        pass

    def setPerson(self,JSON_MSG):
        self.Name = JSON_MSG["boletin"]["Person_Victim"]["Name"]
        self.CPF = JSON_MSG["boletin"]["Person_Victim"]["CPF"]
        self.RG = JSON_MSG["boletin"]["Person_Victim"]["RG"]
        self.Date_Of_Birth = JSON_MSG["boletin"]["Person_Victim"]["Date_Of_Birth"]
        self.Sex = JSON_MSG["boletin"]["Person_Victim"]["Sex"]
        pass


class OccurrenceBoletin:
    def __init__(self):
        self.Person_Victim = None
        self.Person_accused = None
        self.Desciption_accused = None
        self.Local = None
        self.Using_Weapon = None
        self.Weapon = None
        self.Name_Responsible_For_Case = None
        self.StatusCase = None
        pass

    def setOccurrenceBoletin(self,JSON_MSG):
        self.Person_Victim = JSON_MSG["boletin"]["Person_Victim"]
        self.Person_accused = JSON_MSG["boletin"]["Person_accused"]
        self.Desciption_accused = JSON_MSG["boletin"]["Desciption_accused"]
        self.Local = JSON_MSG["boletin"]["Local"]
        self.Using_Weapon = JSON_MSG["boletin"]["Using_Weapon"]
        self.Weapon = JSON_MSG["boletin"]["Weapon"]
        self.Name_Responsible_For_Case = JSON_MSG["boletin"]["Name_Responsible_For_Case"]
        self.StatusCase = JSON_MSG["boletin"]["StatusCase"]
        pass


class Message:
    def __init__(self):
        self.MessageType = None
        self.requestId = None
        self.MethodReference = None
        self.MethodId = None
        self.arguments = None
        self.boletin = None
        pass

    def setMessage(self,JSON_MSG):
        self.MessageType = JSON_MSG["MessageType"]
        self.requestId = JSON_MSG["requestId"]
        self.MethodReference = JSON_MSG["MethodReference"]
        self.MethodId = JSON_MSG["MethodId"]
        self.arguments = JSON_MSG["arguments"]
        self.boletin = JSON_MSG["boletin"]
        pass
# ------------------------> Main <--------------------------------


sc=SocketServer(serverPort=2006, serverHost="127.0.0.1")
(conn,addr)=sc.socket.accept()
print("Cliente -> ",addr)
msg=sc.read(conn=conn)
(mensagem,bo,pessoa)=setAll(msg)
print("-------------------   MENSAGEM   ----------------------")
print(mensagem.MessageType,mensagem.requestId,mensagem.MethodReference,mensagem.MethodId,mensagem.arguments,mensagem.boletin)
print("----------------------  BO  --------------------------")
print(bo.Person_Victim,bo.Person_accused,bo.Desciption_accused,bo.Local,bo.Using_Weapon,bo.Weapon,bo.Name_Responsible_For_Case,bo.StatusCase)
print("---------------------   Person  ----------------------")
print(pessoa.Name,pessoa.CPF,pessoa.RG,pessoa.Date_Of_Birth,pessoa.Sex)

bo.StatusCase = True
sc.send(bo.StatusCase,conn=conn)
conn.close()
