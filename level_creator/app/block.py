import pygame

class Block:
    def __init__(self, x, y, bs, type) -> None:
        self.x = x
        self.y = y
        self.width = bs
        self.height = bs
        self.type = type

    def display(self, screen: pygame.surface, image_map, current_block):
        if self.mouse_over():
            if (current_block == "none"):
                pygame.draw.rect(screen, (255, 200, 200), (self.x, self.y, self.width, self.height))
            else:
                screen.blit(pygame.transform.scale(image_map[current_block], (self.width, self.height)), (self.x, self.y))
        elif self.type == "none":
            pass
        else:
            screen.blit(pygame.transform.scale(image_map[self.type], (self.width, self.height)), (self.x, self.y))
            

    def mouse_over(self):
        mx, my = pygame.mouse.get_pos()
        return mx > self.x and my > self.y and mx < self.x + self.width and my < self.y + self.height
    
    def set_type(self, new_type):
        self.type = new_type

    def get_type(self):
        return self.type