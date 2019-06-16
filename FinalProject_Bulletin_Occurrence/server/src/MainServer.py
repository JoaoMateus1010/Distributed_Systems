import fileinput
import socket
import json
import threading
import time
# ------------------------> Global Variables <--------------------------------
from distutils import file_util

listpersonvic = list()
listpersonacc = list()
listbo = list()
listmsg = list()
dictRelationBO = dict()
dictRelationMsg = dict()
dictRelationBOFile = dict()
mapFuncServer = dict({1: "ADDBO", 2: "BuscarBOVitima", 3: "BuscarBOAcusado"})
# ------------------------> Functions <--------------------------------

def StrToJson(msg):
    return json.loads(msg)

def setAll(msg):
    msgjson = StrToJson(msg)
    personVic = PersonVic()
    personAcc = PersonAcc()
    bo = OccurrenceBoletin()
    msgRequest = Message()
    personVic.setPersonVic(JSON_MSG=msgjson)
    personAcc.setPersonAcc(JSON_MSG=msgjson)
    bo.setOccurrenceBoletin(JSON_MSG=msgjson)
    msgRequest.setMessage(JSON_MSG=msgjson)
    return (msgRequest,bo,personVic,personAcc)

def MountMsg(Msg,BO,PersonVIC,PersonACC):
    Newvic = PersonVic()
    Newacc = PersonAcc()
    Newob = OccurrenceBoletin()
    NewMsg = Message()

    Newob = BO
    NewMsg = Msg
    Newvic = PersonVIC
    Newacc = PersonACC

    Newob.Person_Victim = Newvic
    Newob.Person_accused = Newacc
    NewMsg.boletin = Newob

    return NewMsg.ConvertObjectMessageToJSON()
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


class PersonVic:
    def __init__(self):
        self.Name = None
        self.CPF = None
        self.RG = None
        self.Date_Of_Birth = None
        self.Sex = None
        pass

    def setPersonVic(self,JSON_MSG):
        self.Name = JSON_MSG["boletin"]["Person_Victim"]["Name"]
        self.CPF = JSON_MSG["boletin"]["Person_Victim"]["CPF"]
        self.RG = JSON_MSG["boletin"]["Person_Victim"]["RG"]
        self.Date_Of_Birth = JSON_MSG["boletin"]["Person_Victim"]["Date_Of_Birth"]
        self.Sex = JSON_MSG["boletin"]["Person_Victim"]["Sex"]
        pass

    def ConvertObjectPersonVICToJSON(self):
        return json.dumps({"Name":self.Name,"CPF":self.CPF,"RG":self.CPF,"Date_Of_Birth":self.Date_Of_Birth,"Sex":self.Sex})


class PersonAcc:
    def __init__(self):
        self.Name = None
        self.CPF = None
        self.RG = None
        self.Date_Of_Birth = None
        self.Sex = None
        pass

    def setPersonAcc(self,JSON_MSG):
        self.Name = JSON_MSG["boletin"]["Person_accused"]["Name"]
        self.CPF = JSON_MSG["boletin"]["Person_accused"]["CPF"]
        self.RG = JSON_MSG["boletin"]["Person_accused"]["RG"]
        self.Date_Of_Birth = JSON_MSG["boletin"]["Person_accused"]["Date_Of_Birth"]
        self.Sex = JSON_MSG["boletin"]["Person_accused"]["Sex"]
        pass

    def ConvertObjectPersonACCToJSON(self):
        return json.dumps({"Name": self.Name, "CPF":self.CPF, "RG": self.RG, "Date_Of_Birth": self.Date_Of_Birth, "Sex": self.Sex})


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

    def ConvertObjectOccurrenceBoletinToJSON(self):
        pacc = PersonAcc()
        pvic = PersonVic()

        pacc = self.Person_accused
        pvic = self.Person_Victim

        return json.dumps({"Person_Victim":pvic.ConvertObjectPersonVICToJSON(),"Person_accused":pacc.ConvertObjectPersonACCToJSON(),"Desciption_accused":self.Desciption_accused,"Local":self.Local,"Using_Weapon":self.Using_Weapon,"Weapon":self.Weapon,"Name_Responsible_For_Case":self.Name_Responsible_For_Case,"StatusCase":self.StatusCase})


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

    def ConvertObjectMessageToJSON(self):
        bo = OccurrenceBoletin()
        bo = self.boletin
        return json.dumps({"MessageType":self.MessageType,"requestId":self.requestId,"MethodReference":self.MethodReference,"MethodId":self.MethodId,"arguments":self.arguments,"boletin":bo.ConvertObjectOccurrenceBoletinToJSON()})

# ------------------------> Functions Server <--------------------------------

def addPersonVic(person):
    aux = True
    for vic in listpersonvic:
        if(vic.Name==person.Name):
            aux=False
    if(aux):
        listpersonvic.append(person)
    pass


def addPersonAcc(person):
    aux = True
    for acc in listpersonacc:
        if (acc.Name == person.Name):
            aux = False
    if (aux):
        listpersonacc.append(person)
    pass

def addBO(bo):
    listbo.append(bo)
    pass


def addMsg(msg):
    listmsg.append(msg)
    pass

def listPersonVic():
    print("   >>   " + "Vitimas"+"   <<   ")
    for vic in listpersonvic:
        print("    > "+str(vic.Name))
    pass


def listPersonAcc():
    print("   >>   " + "Acusados" + "   <<   ")
    for acc in listpersonacc:
        print("    > "+str(acc.Name))
    pass


def listBO():
    print("   >>   " + "Vítimas <-> Acusados -> Nome do responsável pelo caso ---> Status do caso" + "   <<   ")
    for (key,value) in dictRelationBO.items():
        print("    >",str(value[0].Name)," <-> ",str(value[1].Name)," -> ",str(key.Name_Responsible_For_Case)," ---> ",str(key.StatusCase),"  <<  ")
    pass


def listMSG():
    print("   >>   " + "(Tipo da mensagem, ID da mensagem, Referência do método)" + "   <<   ")
    for msg in listmsg:
        print("    >",(msg.MessageType,msg.requestId,msg.MethodReference))
    pass

# ------------------------> Remote <--------------------------------
def ADDBO(mensagem, bo, pessoaVic, pessoaAcc,conn,sc):
    newpv = PersonVic()
    newpc = PersonAcc()
    newbo = OccurrenceBoletin()
    newmsg = Message()

    newpv = pessoaVic
    newpc = pessoaAcc
    newbo= bo
    newmsg = mensagem

    newmsg.MessageType = 1

    addPersonVic(newpv)
    addPersonAcc(newpc)
    addBO(newbo)
    addMsg(newmsg)
    dictRelationBO[newbo]= (newpv,newpc)
    dictRelationMsg[newmsg] = newbo

    sc.send(str(MountMsg(newmsg, newbo, newpv, newpc)), conn=conn)
    pass


def BuscarBOVitima(mensagem, bo, pessoaVic, pessoaAcc,conn,sc):
    namefile=str(pessoaVic.Name)
    path = str("client/src/FILE_PERSON_VICTIM/")+str(namefile)
    file = open(path,"w")
    st = str()
    for (key,value) in dictRelationBO.items():
        if(value[0].Name==pessoaVic.Name):
            st += "----> BO: "+str(key)+" <----\n"
            st += "VICTIM : ("+str("Nome: "+str(value[0].Name)+",CPF: "+str(value[0].CPF)+",RG: "+str(value[0].RG)+",Sexo: "+str(value[0].Sex))+")\n"
            st += "ACCUSED : ("+str("Nome: "+str(value[1].Name)+",CPF: "+str(value[1].CPF)+",RG: "+str(value[1].RG)+",Sexo: "+str(value[1].Sex))+")\n"
            st += "DESCRIPTION_ACCUSED : "+str(key.Desciption_accused)+"\n"
            st += "LOCAL : "+str(key.Local)+"\n"
            st += "USING_WEAPON : "+str(key.Using_Weapon)+"\n"
            st += "WEAPON : "+str(key.Weapon)+"\n"
            st += "NAME RESPONSIBLE FOR CASE : "+str(key.Name_Responsible_For_Case)+"\n"
            st += "STATUS CASE : "+str(key.StatusCase)+"\n"

    file.write(st)
    newpv = PersonVic()
    newpc = PersonAcc()
    newbo = OccurrenceBoletin()
    newmsg = Message()

    newpv = pessoaVic
    newpc = pessoaAcc
    newbo = bo
    newmsg = mensagem

    newmsg.MessageType = 1
    sc.send(str(MountMsg(newmsg, newbo, newpv, newpc)), conn=conn)
    file.close()
    pass


def BuscarBOAcusado(mensagem, bo, pessoaVic, pessoaAcc,conn,sc):
    namefile = str(pessoaAcc.Name)
    path = str("client/src/FILE_PERSON_ACCUSED/") + str(namefile)
    file = open(path, "w")
    st = str()
    for (key, value) in dictRelationBO.items():
        if (value[1].Name == pessoaAcc.Name):
            st += "----> BO: " + str(key) + " <----\n"
            st += "VICTIM : (" + str("Nome: " + str(value[0].Name) + ",CPF: " + str(value[0].CPF) + ",RG: " + str(
                value[0].RG) + ",Sexo: " + str(value[0].Sex)) + ")\n"
            st += "ACCUSED : (" + str("Nome: " + str(value[1].Name) + ",CPF: " + str(value[1].CPF) + ",RG: " + str(
                value[1].RG) + ",Sexo: " + str(value[1].Sex)) + ")\n"
            st += "DESCRIPTION_ACCUSED : " + str(key.Desciption_accused) + "\n"
            st += "LOCAL : " + str(key.Local) + "\n"
            st += "USING_WEAPON : " + str(key.Using_Weapon) + "\n"
            st += "WEAPON : " + str(key.Weapon) + "\n"
            st += "NAME RESPONSIBLE FOR CASE : " + str(key.Name_Responsible_For_Case) + "\n"
            st += "STATUS CASE : " + str(key.StatusCase) + "\n"

    file.write(st)
    newpv = PersonVic()
    newpc = PersonAcc()
    newbo = OccurrenceBoletin()
    newmsg = Message()

    newpv = pessoaVic
    newpc = pessoaAcc
    newbo = bo
    newmsg = mensagem

    newmsg.MessageType = 1
    sc.send(str(MountMsg(newmsg, newbo, newpv, newpc)), conn=conn)
    file.close()
    pass
# ------------------------> Switch Functions <--------------------------------


def selectFunc(mensagem, bo, pessoaVic, pessoaAcc,conn,sc):
    valmsg = Message()
    valmsg = mensagem
    try:
        if(valmsg.MethodId == 1):
            ADDBO(mensagem, bo, pessoaVic, pessoaAcc,conn,sc)
            pass
        if(valmsg.MethodId == 2):
            BuscarBOVitima(mensagem, bo, pessoaVic, pessoaAcc,conn,sc)
            pass
        if(valmsg.MethodId == 3):
            BuscarBOAcusado(mensagem, bo, pessoaVic, pessoaAcc,conn,sc)
            pass

    except KeyError:
        print("Função não encontrada")
    pass


def listen():
    sc = SocketServer(serverPort=2009, serverHost="127.0.0.1")
    print("[SERVER OK]")
    while (True):
        (conn, addr) = sc.socket.accept()
        print("\n-------- Cliente Connected ------")
        print("Client: ", addr)
        msg = sc.read(conn=conn)
        (mensagem, bo, pessoaVic, pessoaAcc) = setAll(msg)
        print("Op Request: ",mensagem.MethodReference)
        selectFunc(mensagem, bo, pessoaVic, pessoaAcc,conn,sc)
        print("-------- End ------\nDigite: ")
        conn.close()
    pass
# ------------------------> Main <--------------------------------------------


thr = threading.Thread(target=listen)
thr.start()
print("[MENU OK]")
time.sleep(1)
while(True):
    print("[1] : Listar Vitimas\n[2] : Listar Acusados\n[3] : Listar Boletins de ocorrências\n[4] : Listar Mensagens\n[5] : Atribuir caso ao Responsável\n[6] : Concluir caso")
    op = input("Digite: ")
    if(op=="1"):
        listPersonVic()
    if (op == "2"):
        listPersonAcc()
    if(op=="3"):
        listBO()
    if(op=="4"):
        listMSG()
    if(op=="5"):
        listMSG()
        selectMsgID = int(input("Selecione o ID da mensagem desejado : "))
        selectMsg = Message()
        for mg in listmsg:
            if(mg.requestId == selectMsgID):
                selectMsg = mg
        if(selectMsg.MethodReference != None):
            NameResp = input("Digite o nome do responsável do caso: ")
            dictRelationMsg[selectMsg].Name_Responsible_For_Case = NameResp
            print("> Alteração realizada com sucesso")
        else:
            print(" ! Mensagem não encontrada com o ID digitado ! ")
    if(op=="6"):
        listMSG()
        selectMsgID = int(input("Selecione o ID da mensagem desejado : "))
        selectMsg = Message()
        for mg in listmsg:
            if (mg.requestId == selectMsgID):
                selectMsg = mg
        if (selectMsg.MethodReference != None):
            Resp = input("Deseja concluir o caso? [s/n]: ")
            newStatusCase = False
            if(Resp == "s"):
                newStatusCase = True
            dictRelationMsg[selectMsg].StatusCase = newStatusCase
            print("> Alteração realizada com sucesso")
        else:
            print(" ! Mensagem não encontrada com o ID digitado ! ")
