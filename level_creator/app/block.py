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
        if self.type != "none":
            self.display_block(screen, image_map)
        self.display_mouse_over(screen, image_map, current_block)
    
    def display_mouse_over(self, screen, image_map, current_block):
        if self.mouse_over():
            if current_block != "none":
                screen.blit(pygame.transform.scale(image_map[current_block], (self.width, self.height)), (self.x, self.y))
            screen.blit(pygame.transform.scale(self.selected_img, (self.width, self.height)), (self.x, self.y))

    def display_block(self, screen, image_map):
        if self.type == 'P':
            screen.blit(pygame.transform.scale(image_map[self.type], 
                                               (self.width * 2, self.height * 2)), 
                                               (self.x - self.width / 2, self.y - self.height / 2))
        else:
            screen.blit(pygame.transform.scale(image_map[self.type], (self.width, self.height)), (self.x, self.y))

    def mouse_over(self):
        mx, my = pygame.mouse.get_pos()
        return mx > self.x and my > self.y and mx < self.x + self.width and my < self.y + self.height
    
    def set_type(self, new_type):
        self.type = new_type

    def get_type(self):
        return self.type