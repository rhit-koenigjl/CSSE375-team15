import json
import pygame

class ImageManager:
    def __init__(self, filepath) -> None:
        f = open(filepath)
        j = json.load(f)
        collections = list(j['assets'])

        self.image_map = {}

        for c in collections:
            for asset in c:
                self.image_map[asset[0]] = pygame.image.load(asset[1])

    def get_keys(self):
        return self.image_map.keys()
    
    def get_image_map(self):
        return self.image_map
    
    def get_image(self, type):
        return self.image_map[type]