import json
import pygame

class ImageManager:
    def __init__(self, filepath) -> None:
        f = open(filepath)
        images = json.load(f)['images']

        self.image_map = {}

        for i in images:
            # 0 index is name, 1 index is file name
            self.image_map[i[0]] = pygame.image.load(i[1])

    def get_keys(self):
        return self.image_map.keys()
    
    def get_image_map(self):
        return self.image_map