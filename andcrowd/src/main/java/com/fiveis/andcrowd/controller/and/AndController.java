package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.service.and.AndService;
import com.fiveis.andcrowd.service.user.DynamicUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/and")
public class AndController {

    private final AndService andService;

    @Autowired
    public AndController(AndService andService) {
        this.andService = andService;
    }

    @RequestMapping("/list")
    public List<AndDTO.Find> getList() {
        return andService.findAllByIsDeletedFalse();
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> createItem(@RequestBody And and) {
        andService.save(and);
        // 생성된 andId 값을 응답 Body에 포함하여 클라이언트에게 전달
        return ResponseEntity.ok(and.getAndId());
    }


    @GetMapping("/{andId}")
    public AndDTO.Find getItem(@PathVariable("andId") int andId) {
        return andService.findById(andId).orElse(null);
    }

    @PutMapping("/{andId}/updateView")
    public ResponseEntity<String> updateView(@PathVariable("andId") int andId) {
        andService.updateView(andId);
        return ResponseEntity.ok("View count updated successfully.");
    }

    @PostMapping("/{andId}/like/{userId}")
    public ResponseEntity<String> updateLike(@PathVariable("andId") int andId, @PathVariable("userId") int userId) {
        andService.updateLike(andId, userId);
        return ResponseEntity.ok("like count updated successfully.");
    }

    @GetMapping("/{andId}/like/{userId}")
    public boolean isLike(@PathVariable("andId") int andId, @PathVariable("userId") int userId) {
        return andService.isLiked(andId, userId);
    }

    @GetMapping("/{myId}/follow/{userId}")
    public boolean isFollowed(@PathVariable("myId") int myId, @PathVariable("userId") int userId) {
        return andService.isFollowed(myId, userId);
    }

    @PostMapping("/{myId}/follow/{userId}")
    public ResponseEntity<String> updateFollow(@PathVariable("myId") int myId, @PathVariable("userId") int userId) {
        andService.updateFollow(myId, userId);
        return ResponseEntity.ok("follow updated successfully.");
    }



    @RequestMapping(value="/{andId}/update" , method=RequestMethod.PATCH)
    public void updateAnd( @RequestBody And and) {
        andService.update(and);
    }

    @RequestMapping(value="/{andId}/update/status" , method=RequestMethod.PATCH)
    public void updateAndStatus( @PathVariable("andId") int andId) {
        andService.updateStatus(andId);
    }

    @RequestMapping("/{andId}/delete")
    public void deleteItem(@PathVariable("andId") int andId) {
        andService.deleteById(andId);
    }
}
