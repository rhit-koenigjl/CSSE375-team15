import pygame

class Block:
    def __init__(self, x, y, bs, type) -> None:
        self.x = x
        self.y = y
        self.width = bs
        self.height = bs
        self.type = type
        self.selected_img = pygame.image.load("assets\\selected_square.png")

    def display(self, screen: pygame.surface, image_map, current_block):
        if self.type == "none":
            pass
        else:
            screen.blit(pygame.transform.scale(image_map[self.type], (self.width, self.height)), (self.x, self.y))
        if self.mouse_over():
            if current_block != "none":
                screen.blit(pygame.transform.scale(image_map[current_block], (self.width, self.height)), (self.x, self.y))
            screen.blit(pygame.transform.scale(self.selected_img, (self.width, self.height)), (self.x, self.y))
            

    def mouse_over(self):
        mx, my = pygame.mouse.get_pos()
        return mx > self.x and my > self.y and mx < self.x + self.width and my < self.y + self.height
    
    def set_type(self, new_type):
        self.type = new_type

    def get_type(self):
        return self.type