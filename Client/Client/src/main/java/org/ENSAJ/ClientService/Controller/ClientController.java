package org.ENSAJ.ClientService.Controller;

import org.ENSAJ.ClientService.ClientApplication;
import org.ENSAJ.ClientService.CustomMessage;
import org.ENSAJ.ClientService.MQConfig;
import org.ENSAJ.ClientService.Model.Client;
import org.ENSAJ.ClientService.Model.Voiture;
import org.ENSAJ.ClientService.Service.ClientService;
//import org.ENSAJ.ClientService.Model.Voiture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
public class ClientController {

    @Autowired
    private RabbitTemplate template;



    @FeignClient(name="SERVICE-VOITURE")
    interface VoitureService{
        @GetMapping(path="/voitures/client/{id}")
        public  List<Voiture> voitureByClientId(@PathVariable Long id);
    }
    @Autowired
    VoitureService voitureService;
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<Client> chercherClient(){
        CustomMessage message = new CustomMessage();
        message.setMessage(""+clientService.retournerListeClients());
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);

        return clientService.retournerListeClients();
    }

    @GetMapping("/clients/{id}")
    public Client chercherUnClient(@PathVariable Long id) throws Exception {
        CustomMessage message = new CustomMessage();
        message.setMessage(""+clientService.retournerClientById(id));
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);

        return clientService.retournerClientById(id);
    }

    @PostMapping("/clients")
    public Client enregistrerUnClient(@RequestBody Client client){
//        template.convertAndSend(MQConfig.EXCHANGE,
//            MQConfig.ROUTING_KEY,client);
        CustomMessage message = new CustomMessage();
        message.setMessage(""+client);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);

        return clientService.enregistrerClient(client);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> modifierUnClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
        return clientService.modifierClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteUnClient(@PathVariable Long id){
        clientService.supprimerClient(id);
    }


}
