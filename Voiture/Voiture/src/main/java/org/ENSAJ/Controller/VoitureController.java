package org.ENSAJ.Controller;

import org.ENSAJ.CustomMessage;
import org.ENSAJ.MQConfig;
import org.ENSAJ.Model.Voiture;
import org.ENSAJ.Repository.VoitureRepository;
import org.ENSAJ.Service.VoitureService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class VoitureController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    VoitureRepository voitureRepository;

    @Autowired
    VoitureService voitureService;

    @GetMapping(value ="/voitures", produces = {"application/json"})
    public ResponseEntity<List<Voiture>> chercherVoiture(){
        CustomMessage message = new CustomMessage();
        message.setMessage("Toutes les voitures: "+voitureRepository.findAll());
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);

        return ResponseEntity.ok(voitureRepository.findAll());
    }

    @GetMapping("/voitures/{Id}")
    public Voiture chercherUneVoiture(@PathVariable Long Id) throws Exception{
        CustomMessage message = new CustomMessage();
        message.setMessage("la voiture choisie : "+voitureRepository.findById(Id));
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);
        return voitureRepository.findById(Id).orElseThrow(() -> new Exception("Voiture Introuvable"));
    }

//    @GetMapping("/voitures/client/{Id}")
//    public List<Voiture> chercherVoitureParClient(@PathVariable Long Id){
//        return voitureRepository.findByClientId(Id);
//    }

    @PostMapping("/voitures")
    public Voiture enregistrerUneVoiture(@RequestBody Voiture voiture){
        CustomMessage message = new CustomMessage();
        message.setMessage("Voiture : "+voiture);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
            MQConfig.ROUTING_KEY, message);
        return voitureService.enregistrerVoiture(voiture);
    }



}
